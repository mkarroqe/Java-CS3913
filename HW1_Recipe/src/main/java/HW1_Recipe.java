/*
• Develop the Ingredient class.
• Develop the Recipe class. For the Recipe class, you should have a toString function which
returns the ingredients, followed by two blank lines(\r\n\r\n), followed by the steps (one per
line)
• Develop a main which will create a recipe, add some values and print it to the screen.
 */

/**
 *
 * @author mkarroqe
 */

import java.utils;

public class HW1_Recipe {
    public static void main(String []args) {
        // create a recipe
        Ingredient flour = new Ingredient(0.5, "flour");
        Ingredient sugar = new Ingredient(0.5, "sugar");
        Ingredient eggs = new Ingredient(2, "eggs");
        
        Ingredient[] ingredients = new Ingredient[3];
        ingredients[0] = flour;
        ingredients[1] = sugar;
        ingredients[2] = eggs;
        
        Step one = new Step(1, "Mix your dry ingredients in a bowl.");
        Step two = new Step(2, "Put a saucepan on medium heat.");
        Step three = new Step(3, "Put your dry ingredients in the hot pan. Allow the sugar to caramalize.");
        Step four = new Step(4, "Stir in the eggs, making sure not to scramble them.");
        Step five = new Step(5, "Realize the flour was a mistake as everything starts to burn.");
        Step six = new Step(6, "Chuck it in the bin and turn on Great British Bake Off.");
        Step seven = new Step(7, "Pretend you know things by predicting contenstant failures.");
        
        Step[] steps = new Step[7];
        steps[0] = one;
        steps[1] = two; 
        steps[2] = three;
        steps[3] = four;
        steps[4] = five;
        steps[5] = six;
        steps[6] = seven;       
        
        Recipe custardMaybe = new Recipe(ingredients, steps);        
        System.out.println(custardMaybe);
    }
}

class Ingredient {
    double measurement;
    String item;
    
    Ingredient(double _measurement, String _item) {
        measurement = _measurement;
        item = _item;
    }
    
    @Override
    public String toString() {
        return "\t - " + measurement + " units of " + item + "\n";
    }
}

class Step {
    int number;
    String instruction;
    
    Step(int _number, String _instruction) {
        number = _number;
        instruction = _instruction;
    }
    
    @Override
    public String toString() {
        return "\t" + number + ": " + instruction + "\n";
    }
    
}

class Recipe {
    Ingredient[] ingredients;
    Step[] steps;
    
    Recipe(Ingredient[] _ingredients, Step[] _steps) {
        // is this a deep copy?
        ingredients = _ingredients;
        steps = _steps;
    }

    @Override
    public String toString() {
        String output = "Ingredients: \n";
        // returns ingredients
        for (int i = 0; i < ingredients.length; i++) {
            output += (ingredients[i] + " ");
        }
     
        // two blank lines (\r\n\r\n)
        output += "\r\n\r\n";
        output += "Steps: \n";
        // steps (one per line)
        for (int i = 0; i < steps.length; i++) {
            output += (steps[i] + " ");
        }
        
        return output;
    }
}
