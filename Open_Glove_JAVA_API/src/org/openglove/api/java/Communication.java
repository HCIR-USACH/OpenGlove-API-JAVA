/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openglove.api.java;

import com.fazecast.jSerialComm.*;
import java.util.Scanner;

/**
 * Represents  a comunication instance between the API and the glove.
 * Provide methods for send and receive data through serial port
 * @author Rodrigo Monsalve Lagos
 */
public class Communication {
    
    SerialPort port;
    
    /**
     * Initialize an instance of Communication class without open the communication with the device
     */
    public Communication()
    {
    }

    /**
     * Initialize an instance of Communication class, opening the communication using the specified port and baudrate
     * @param portName Name of the serial port to open a communication
     * @param baudRate Data rate in bits per second. Use one of these values: 300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 28800, 38400, 57600, or 115200
     */
    public Communication(String portName, int baudRate)  
    {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(baudRate);
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        
    }

    /**
     * List all active serial ports names
     * @return An array with the names of all active serial ports
     */
    public String[] getPortNames() {

        String [] portNames = new String [SerialPort.getCommPorts().length];
        for (int i=0; i < SerialPort.getCommPorts().length; i++){
        
            portNames[i] = SerialPort.getCommPorts()[i].getSystemPortName();
            
        }
        return portNames;      
    }

    /**
     * Open a new connection with the specified port and baudrate
     * @param portName Name of the serial port to open a communication
     * @param baudRate Data rate in bits per second. Use one of these values: 300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 28800, 38400, 57600, or 115200
     * @return Return true if the port is opened sucessfully
     */
    public boolean openPort(String portName, int baudRate)
    {
        
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(baudRate);
        boolean portOpen = port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 200, 0);
        return portOpen;
        

    }

    /**
     * Send the string to the serial port
     * @param data String data to send
     */
    public void write(String data)
    {
        byte[] buffer = data.getBytes();
        port.writeBytes(buffer, buffer.length);
    }

    /**
     * Read the input buffet until a next line character
     * @return A string without the next line character
     */
    public String readLine()
    {
        Scanner scanner = new Scanner(port.getInputStream());
        String line="";
		
	if(scanner.hasNextLine()){
        
            line = scanner.nextLine();
        }
        
        scanner.close();	
        return line;
        
    }

    /**
     * Close the serial communication
     */
    public void closePort()
    {
        port.closePort();
    }
   
}
