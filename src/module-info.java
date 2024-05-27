module test.oopnhom6 {
    requires javafx.controls;
    requires javafx.fxml;
   // requires gson;
    requires com.google.gson;
    opens test.oopnhom6 to javafx.fxml;
    exports test.oopnhom6;
    exports data;
    opens data to com.google.gson, javafx.base, javafx.fxml;
    exports helper;
    opens helper to com.google.gson, javafx.base, javafx.fxml;
    //   requires javafx.controls;
  //  requires javafx.fxml;
//    requires javafx.graphics;
//    opens controller;
//    opens application to javafx.graphics, javafx.fxml;
//


//


}