package twin.developers.projectmqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClient {
    private static final String BROKER = "tcp://broker.emqx.io:1883";
    private static final String CLIENT_ID = "AndroidClient";
    private static final String TOPIC = "peliculas/resenas";
//deberpia funcionar pero ni idea de q pasa, probé con todos los codigos posibles y nada
    private org.eclipse.paho.client.mqttv3.MqttClient mqttClient;

    public MqttClient() {
        try {
            mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());
            mqttClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publishMessage(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes());
            mqttClient.publish(TOPIC, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
