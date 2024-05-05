module test.oopnhom6 {
    requires javafx.controls;
    requires javafx.fxml;
   // requires gson;
    requires com.google.gson;
     opens data to com.google.gson;
    opens test.oopnhom6 to javafx.fxml;
    exports test.oopnhom6;
 //   requires javafx.controls;
  //  requires javafx.fxml;
//    requires javafx.graphics;
//    opens controller;
//    opens application to javafx.graphics, javafx.fxml;
//

//    requires javafx.base;
//


}