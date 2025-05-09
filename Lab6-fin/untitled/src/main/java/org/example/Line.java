package org.example;

import java.io.Serializable;

public record Line(Point start, Point end) implements Serializable {}
