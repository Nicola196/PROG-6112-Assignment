/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package section.b;

public class Food extends MenuItem {

    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Food";
    }
}
