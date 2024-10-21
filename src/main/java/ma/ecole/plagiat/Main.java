package ma.ecole.plagiat;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = sb1;  // sb2 pointe vers le même objet que sb1

        // Affiche les valeurs initiales
        System.out.println("Avant modification:");
        System.out.println("sb1: " + sb1);  // "Hello"
        System.out.println("sb2: " + sb2);  // "Hello"

        // Modifie le contenu via sb1
        sb1.append(" World");

        // Après modification via sb1, sb2 voit également le changement
        System.out.println("Après modification:");
        System.out.println("sb1: " + sb1);  // "Hello World"
        System.out.println("sb2: " + sb2);  // "Hello World" (car sb2 et sb1 pointent vers le même objet)


        String s1 = "Hello";
        String s2 = s1;  // s2 pointe vers le même objet que s1

        // Affiche les valeurs initiales
        System.out.println("Avant modification:");
        System.out.println("s1: " + s1);  // "Hello"
        System.out.println("s2: " + s2);  // "Hello"

        // Modifie s1 en concaténant une nouvelle chaîne (nouvel objet est créé)
        s1 = s1.concat(" World");

        // Après modification de s1, s2 ne change pas car s1 pointe maintenant vers un nouvel objet
        System.out.println("Après modification:");
        System.out.println("s1: " + s1);  // "Hello World"
        System.out.println("s2: " + s2);  // "Hello" (car s1 et s2 ne pointent plus vers le même objet)
    }
}
