package aryex;

import UI.GreetClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetServerTest {
    GreetClient client;
    public GreetServerTest(){
        client = new GreetClient();
    }
    @Test
    public void test(){
        try{
            client.startConnection("10.0.0.153",7070);

            String resp1 = client.sendMessage("hello");
            String resp5 = client.sendMessage("haha");

//            assertEquals(null, resp5);
            client.stopConnection();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}