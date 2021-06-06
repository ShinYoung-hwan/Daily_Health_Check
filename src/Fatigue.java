
public class Fatigue extends Symptoms{

	public Fatigue() {
		super("Fatigue", 6, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Cambridge Dictionary",
				"extreme tiredness\n");
	}
}
