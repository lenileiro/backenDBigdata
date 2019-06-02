package main;

import org.apache.accumulo.core.client.*;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.accumulo.core.security.ColumnVisibility;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws Exception {
        //logger
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        App.run();
    }

    //create slf4j logger
    static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static void run() throws Exception {
        //give the table to be created a name
        String tableName = "users";
        // Read log properties from file
        Properties logProps = new Properties();
        logProps.load(new FileInputStream("/opt/hadoop/share/hadoop/tools/sls/sample-conf/log4j.properties"));
        PropertyConfigurator.configure(logProps);

        // Read client properties from file
        Properties props = new Properties();
        props.load(new FileInputStream("/opt/accumulo/conf/accumulo-client.properties"));

        try (AccumuloClient client = Accumulo.newClient().from(props).build()){

            try {
                client.tableOperations().create(tableName);
            } catch (TableExistsException e) {
                LOGGER.warn("Table " + tableName + " currently exists");
            }

            //Write data to the server
            Mutation mut = new Mutation("employee1");
            ColumnVisibility visibility = new ColumnVisibility();
            mut.at().family("pay").qualifier("salary").visibility(visibility).put("50000");
            mut.at().family("pay").qualifier("period").visibility(visibility).put("monthly");

            //BatchWriterConfig has reasonable defaults
            BatchWriterConfig config = new BatchWriterConfig();
            config.setMaxMemory(10000000L); // bytes available to BatchWriter for buffering mutations

                try {
                    //write to the tableName using a BatchWriter
                    BatchWriter writer = client.createBatchWriter(tableName, config);
                    writer.addMutation(mut);
                    writer.close();
                } catch (TableNotFoundException e) {
                    LOGGER.warn("Error: Table " + tableName + " was not found when executing writer");
                }

            // Read data from server
            // return data with visibilities that match specified auths
            Authorizations auths = new Authorizations();
            try (Scanner scan = client.createScanner(tableName, auths)) {
                scan.setRange(new Range("employee1","employee1"));

                for (Map.Entry<Key,Value> entry : scan) {
                    Text row = entry.getKey().getRow();
                    Value value = entry.getValue();

                    System.out.println(row.toString() + " -> " + value.toString());
                }
            }
        }
    }
}
