/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ruleengine;

/**
 *
 * @author Asus
 */
public class Node {
    String type; // "operator" for AND/OR, "operand" for conditions
    Node left; // Left child
    Node right; // Right child for operators
    String value; // Optional value for operand nodes

    public Node(String type, Node left, Node right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

