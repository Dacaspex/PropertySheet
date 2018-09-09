# PropertySheet
Java Swing property sheet component. Build settings menus on the fly.  

![Example image of property sheet](https://i.imgur.com/gd79Tyi.png)

## How it works, simply
The `PropertySheet` is an augmented `JTable` to wich you can quickly add properties. These are then rendered in a sort of settings menu style.  
```java
IntegerProperty prop1 = new IntegerProperty("My integer property", 42);
FloatProperty prop2 = new FLoatProperty("My float property", 11.12);

PropertySheet sheet = new PropertySheet(new PropertySheetOptions());
sheet.addProperty(prop1);
sheet.addProperty(prop2);
```
### Supperted values out of the box
The following types are supported out of the box with this library:
- Integer
- Long
- Double
- Float
- Color
- Boolean
- String
- Selection (drop down menu)

### Validation
Properties can have numerous (custom) validators to make sure the value is what you want it to be. 
```java
IntegerProperty prop = new IntegerProperty(
   "My integer property",
   42,
   new CompoundValidator(
       new IntegerValidator(),
       new IntegerRangeValidator(30, 50)
   )
);
```
Or even easier with a `Factory`!
```java
IntegerProperty prop = new IntegerProperty(
   "My integer property",
   5,
   new IntegerValidatorFactory()
       .setRange(-5, 10)
       .allowZero(false)
       .build()
);
```
### Custom properties
You can create custom properties with custom renderers and editors (as long as the `JTable` supports it). These can be added via various methods in the `PropertySheet` class. 
