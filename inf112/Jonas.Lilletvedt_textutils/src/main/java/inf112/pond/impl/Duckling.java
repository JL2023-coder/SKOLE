package inf112.pond.impl;

import inf112.pond.Position;

public class Duckling extends Duck {
	public Duckling(Position pos, double size) {
		super(pos, size);
	}

	
	public void step() {
		pos.move(-1, 0);
	}
}
