//Isabella Arão 9265732, Marina Fagundes 9265405

interface Animal { //interface animal 
    String getName(); //metodos abstratos exigidos 
    String getSpecies();
    void eat();
    void sound();
}


interface Ecology { //interface ecologia 
    //metodos abstratos exigidos 
    String getHabitat();
}

abstract class Mammal implements Animal, Ecology { //classe abstrata mamifero que implementa as interfaces animal e ecologia 
    private String name;
    private String species;
    
    // Construtor que aceita um argumento name
    public Mammal(String name, String species) {
        this.name = name;
        this.species = species;
    }
    
    @Override
    public String getName() { //implementacao das funçoes abstratas 
        return name;
    }

    @Override
    public String getSpecies() { //implementacao das funçoes abstratas
        return species;
    }
    
    @Override
    public void eat() { //implementaçao dos metodos abstratos 
        System.out.println("Eats like a mammal."); 
    }
    
    @Override
    public abstract void sound(); //metodo abstrato sound 
    
    @Override
    public abstract String getHabitat(); //metodo abstrato habitat 
    
    public static Animal get(String name, String species) { //metodo usado para criar objetos ao inves da chamada direta ao construtor 
        if (species.equalsIgnoreCase("elephant")) { //verifica se a especie passada eh igual a elefante 
            return new Elephant(name, species);
        } else if (species.equalsIgnoreCase("lion")) { //verifica se a especie passada eh igual a leao 
            return new Lion(name, species);
        } else {
            throw new IllegalArgumentException("Unknown species: " + species); //caso de erro para outras especies 
        }
    }
}

class Elephant extends Mammal { //classe elefante que herda de mamifero 

    public Elephant(String name, String species) { //metodo construtor 
        super(name, species);
    }

    @Override
    public void sound() { //implementacao das funçoes abstratas
        System.out.println("Trumpets");
    }

    @Override
    public String getHabitat() { //implementacao das funçoes abstratas
        return "Desert";
    }
}

class Lion extends Mammal { //classe leao que herda de mamiferos 
    

    public Lion(String name, String species) { //metodo construtor 
        super(name, species);
    }

    @Override
    public void sound() { //implementacao das funçoes abstratas
        System.out.println("Roars");
    }

    @Override
    public String getHabitat() { //implementacao das funçoes abstratas
        return "Savanna";
    }
}

public class Main { 
    public static void main(String[] args) {
        Animal elephant = Mammal.get("Dumbo", "elephant"); //variaveis passadas para elefante e leao 
        Animal lion = Mammal.get("Simba", "lion");

        System.out.println("Elephant:"); //impressao do resultado 
        System.out.println("Name: " + elephant.getName());
        System.out.println("Species: " + elephant.getSpecies());
        elephant.eat();
        elephant.sound();
        System.out.println("Habitat: " + ((Ecology) elephant).getHabitat());

        System.out.println();

        System.out.println("Lion:");
        System.out.println("Name: " + lion.getName());
        System.out.println("Species: " + lion.getSpecies());
        lion.eat();
        lion.sound();
        System.out.println("Habitat: " + ((Ecology) lion).getHabitat());
        
    }
}