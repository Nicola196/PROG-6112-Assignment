/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package section.b;

/**
 *
 * @author RC_Student_lab
 */
    

public class Drink extends MenuItem {

    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Drink";
    }
}
