# OpenGlove-API-JAVA

## Example

```java
OpenGlove glove = new OpenGlove();
glove.openPort("COM3", 9600);

int[] pins = {10, 12};
String[] valuesON = { "HIGH","LOW"};
String[] valuesOFF = { "LOW", "LOW" };

glove.initializeMotor(pins);
glove.activateMotor(pins, valuesON);

```
