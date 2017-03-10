package co.edu.pdam.eci.persistenceapiintegration.network;

import java.io.IOException;

/**
 * Created by estudiante on 3/9/17.
 */
public class NetworkException extends Exception {


    public NetworkException(int i, Object o, IOException e) {
        System.out.println("ERROR --> " + e);
        e.printStackTrace();
    }
}
