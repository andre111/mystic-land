package me.andre111.mystic.client.network;

public interface PacketLink {

    public void sendPacket(Packet packet);

    public void tick();

    public void setPacketListener(PacketListener packetListener);

}