package me.andre111.mystic.client.network;

import java.io.IOException;
import java.net.Socket;

public class ClientPacketLink extends NetworkPacketLink {

    public ClientPacketLink(String host, int port) throws IOException {
        super(new Socket(host, port));
    }

}
