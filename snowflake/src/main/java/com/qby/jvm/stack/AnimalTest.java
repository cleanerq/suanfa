package com.qby.jvm.stack;

public class AnimalTest {
    // 晚期绑定
    public void showEat(Animal animal) {
        animal.eat();
    }

    // 晚期绑定
    public void showHunt(Huntable huntable) {
        huntable.hunt();
    }

    public static void main(String[] args) {

    }
}

class Animal {
    public void eat() {
        System.out.println("动物进食");
    }
}

interface Huntable {
    void hunt();
}

class Dog extends Animal implements Huntable {
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void hunt() {
        System.out.println("狗捕猎");
    }
}

class Cat extends Animal implements Huntable {
    @Override
    public void eat() {
        super.eat();
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("猫捉老鼠");
    }

    public Cat() {
        super();
    }
    public Cat(String name) {
        this();
    }
}