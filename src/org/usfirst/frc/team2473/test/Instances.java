package org.usfirst.frc.team2473.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Instances {
	public static ArrayList<Class<?>> classes = new ArrayList<Class<?>>(); //stores the existing classes used in the program
	public static ArrayList<Object> objects = new ArrayList<Object>(); //stores the instances of those classes existing in this program

	/**
	 * Adds the class to the directory of classes being manipulated with this CollectionModel. 
	 * Also creates the static instance using the default constructor.
	 * @param c the class of which you would like a static instance
	 * @throws InstantiationException if such an object cannot be created
	 * @throws IllegalAccessException if the default constructor is inaccessible
	 * */
	public static void pushModel(Class<?> c) throws InstantiationException, IllegalAccessException {
		if(classes.indexOf(c) == -1) {
			classes.add(c);
			objects.add(c.newInstance());
		}
	}
	
	public static void pushModel(Constructor<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(classes.indexOf(c) == -1) {
			classes.add(c.getDeclaringClass());
			objects.add(c.newInstance());
		}
	}

	/**
	 * Returns the static instance of the given class. If it does not exist, it creates it first, and then returns it.
	 * @param c the class of a static reference is requested
	 * @return a static instance <code>Object</code> of the class c
	 * @throws InstantiationException if such an object cannot be created
	 * @throws IllegalAccessException if the default constructor is inaccessible
	 * */
	public static Object getInstanceOf(Class<?> c) {
		if(classes.indexOf(c) == -1) {
			classes.add(c);
			try {
				objects.add(c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objects.get(classes.indexOf(c));
	}
}