package inf112.pond;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import inf112.pond.impl.Duck;

public class Pond {
	private double x, y;
	private double width, height;

	public Pond(int w, int h) {
		this.width = w;
		this.height = h;
		objs.add(new Duck(Position.create(0, 0), 50));
	}

	public List<PondObject> objs = new ArrayList<>();

	public static Random random = new Random();

	public void add(PondObject obj) {
		objs.add(obj);
	}

	public void step() {

		for (PondObject o : new ArrayList<>(objs)) {
			o.step(this);
		}
	}

	public static void main(String[] args) {
		Pond pond = new Pond(1280, 720);
		String[] chars = new String[100];
		System.out.println("\n\n\n\n");
		for (int step = 0; step < 300; step++) {
			pond.step();
			for (int i = 0; i < chars.length; i++)
				chars[i] = " ";

			int k = 0;
			for (PondObject o : pond.objs) {
				int x = (int) (o.getX() % 96);
				if (x < 0)
					x += 96;
				x = Math.max(0, Math.min(chars.length - 1, x + k++));
				if (o.getSize() >= 50)
					chars[x] = "🦆";
				else if (o.getSize() > 10)
					chars[x] = "🐤";
				else if (o.getSize() > 5)
					chars[x] = "🐣";
				else
					chars[x] = "🥚";
			}
			System.out.print("\r" + String.join("", chars));
			// "animert" versjon: System.out.print("\r" + String.join("", chars));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
