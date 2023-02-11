package lym.interprete;

import java.text.ParseException;
import java.util.ArrayList;

class Environment {
    
    // Enclosing field
    private final Environment enclosing;

    // Variable values
    private final ArrayList<String> identifiers = new ArrayList<>();

    // Environment Constructors

    public Environment() {
        this.enclosing = null;
    }
  
    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    public Environment getEnclosing() {
        return this.enclosing;
    }
    
    // Environment get

    // recupera el valor asociado con el nombre dado
    // El método primero verifica el entorno local para el valor. Si no se encuentra el valor, comprobará el entorno que lo contiene (si existe).
    // Si el valor no se encuentra en ningún entorno de la cadena, se genera un ParseException.
  
    public void check(String name) throws ParseException {
        // Get on this scope
        if (identifiers.contains(name)) {
            return;
        }   
        // Get on higher scope
        else if (enclosing != null) {
            enclosing.check(name);
        }
        // Doesn't exists
        else {
            throw new ParseException("Undefined variable " + name, 0);
        }
    }

    // Definir una variable
    public void define(String name) {
        identifiers.add(name);
    }
 
    @Override
    public String toString() {
        String result = identifiers.toString();
        if (enclosing != null) {
            result += " -> " + enclosing.toString();
        }
        return result;
    }

  }
