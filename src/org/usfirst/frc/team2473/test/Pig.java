package org.usfirst.frc.team2473.test;

public class Pig implements Interface { //see Interface.java
	public static void main(String[] args) {
		//using the static reference of the classes Pig and Animal, cast it to such and apply the print status method		
		((Pig) Instances.getInstanceOf(Pig.class)).printStatus();
		((Animal) Instances.getInstanceOf(Animal.class)).printStatus();
		((Pig) Instances.getInstanceOf(Animal.class)).printStatus();
		//see CollectionModel.java
	}

	//overridden print status method prints pig
	@Override
	public void printStatus() {
		// TODO Auto-generated method stub
		System.out.println("pigs");
	}

}

//another class example for demonstrated use of Interface and CollectionModel
class Animal implements Interface {

	@Override
	public void printStatus() {
		// TODO Auto-generated method stub
		System.out.println("animals");
	}

}