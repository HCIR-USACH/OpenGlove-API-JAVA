/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openglove.api.java;

import java.util.List;

/**
 * Represents an OpenGlove device instance. Provide methods for communicatiopn with the device, initialize and activate vibration motors, besides others actuators and sensors
 * @author Rodrigo Monsalve Lagos
 */
public class OpenGlove {
    
    
    Communication communication = new Communication();
    MessageGenerator messageGenerator = new MessageGenerator();

    /**
     * Open the communication with the port and baudrate specified
     * @param portName Name of the serial port to open a communication
     * @param baudRate Data rate in bits per second. Use one of these values: 300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 28800, 38400, 57600, or 115200
     * @return Return True if the port was opened sucesfully
     */
    public boolean openPort(String portName, int baudRate)
    {
        return communication.openPort(portName,baudRate);
    }

    /**
     * Close the current active serial communication
     */
    public void closePort()
    {
        communication.closePort();
    }

    /**
     * List all active serial ports names
     * @return An array with the names of all active serial ports
     */
    public String[] getPortNames()
    {
        return communication.getPortNames();
    }

    /**
     * Initialize pins like motors in the control software
     * @param pins List of pins that are initialized
     */
    public void initializeMotor(List<Integer> pins)
    {
        String message = messageGenerator.initializeMotor(pins);
        communication.write(message);
    }

    /**
     * Activate motors with analog or digital values. Each motor is activated with the value with the same index
     * @param pins List of pins where are connected the motors
     * @param values List with the intensities to activate the motors. It can be "HIGH" or "LOW" in digital mode or a number bewteen 0 and 255 in analog mode
     */
    public void activateMotor(List<Integer> pins, List<String> values)
    {
        String message = messageGenerator.activateMotor(pins,values);
        communication.write(message);
    }

    /**
     * Read the input buffet until a next line character
     * @return A string without the next line character
     */
    public String readLine()
    {
        return communication.readLine();
    }

    /**
     * 
     * @param message
     */
    public void write(String message)
    {
        communication.write(message);
    }

    /**
     * Returns the input voltage from a analog pin
     * @param pin Number of the analog pin to be readed
     * @return The input voltage readed form the analog pin, between 0 and 1023. This value must be converted to be used like temperature, etc.
     */
    public String analogRead(int pin)
    {
        String message = messageGenerator.analogRead(pin);
        communication.write(message);
        String value = communication.readLine();
        return value;
    }

    /**
     * Returns the value from a digital pin
     * @param pin Number of the digital pin to be readed
     * @return 1 for "HIGH" or 0 for "LOW"
     */
    public String digitalRead(int pin)
    {
        String message = messageGenerator.digitalRead(pin);
        communication.write(message);
        String value = communication.readLine();
        return value;
    }

    /**
     * Initialize a pin in input or output mode
     * @param pin Number of the pin to be initialized
     * @param mode Mode to initialize the pin, it can be "INPUT" or "OUTPUT"
     */
    public void pinMode(int pin, String mode)
    {
        String message = messageGenerator.pinMode(pin,mode);
        communication.write(message);
    }

    /**
     * Initialize multiples pins in input or output mode. Each pin is initialized with the mode in the same index
     * @param pins List with the numbers of the pins to be initialized
     * @param modes List with the modes to initialize the pins, it can be "INPUT" or "OUTPUT"
     */
    public void pinMode(List<Integer> pins, List<String> modes)
    {
        String message = messageGenerator.pinMode(pins, modes);
        communication.write(message);
    }

    /**
     * Write a value to a digital pin
     * @param pin Number of the pin to be writed
     * @param value Value to be write in the pin, it can be "HIGH" or "LOW"
     */
    public void digitalWrite(int pin, String value)
    {
        String message = messageGenerator.digitalWrite(pin, value);
        communication.write(message);
    }

    /**
     * Write values on digital pins. Each motor is activated with the value with the same index
     * @param pins List with the numbers of the pins to be initialized
     * @param values List with the values to write on the pins, it can be "HIGH" or "LOW"
     */
    public void digitalWrite(List<Integer> pins, List<String> values)
    {
        String message = messageGenerator.digitalWrite(pins, values);
        communication.write(message);
    }

    /**
     * Write an analog value to a pin
     * @param pin Number of the pin to be writed
     * @param value Value to be write in the pin, it can be between 0 (always off) and 255 (always on)
     */
    public void analogWrite(int pin, int value)
    {
        String message = messageGenerator.analogWrite(pin, value);
        communication.write(message);
    }

    /**
     * Write analog values to pins. Each motor is activated with the value with the same index
     * @param pins List with the numbers of the pins to be writed
     * @param values List with the values to write on the pins, it can be between 0 (always off) and 255 (always on)
     */
    public void analogWrite(List<Integer> pins, List<Integer> values)
    {
        String message = messageGenerator.analogWrite(pins, values);
        communication.write(message);

    }
    
}
