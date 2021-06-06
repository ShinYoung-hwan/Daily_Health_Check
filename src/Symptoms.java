/* This class is about common symptoms of Covid-19.
 * # Common symptoms
 * Fever: 83~99%
 * Loss of Appetite: 40~84%
 * Fatigue: 44~70%
 * Loss of Smell: 15~30%
 *
 * Shortness of breath: 31~40%
 * Cough: 59~82%
 * Coughing up sputum: 28~33%
 * Muscle aches and pain: 11~35%
 *
 */
public abstract class Symptoms {
	private String nameOfSymptom;
	private int weightOfSymptom;
	private boolean isCatched;

	public Symptoms(String nameOfSymptom, int weightOfSymptom, boolean isCatched) {
		this.nameOfSymptom = nameOfSymptom;
		this.weightOfSymptom = weightOfSymptom;
		this.isCatched = isCatched;
	}

	public String getNameOfSymptom() {
		return nameOfSymptom;
	}

	public void setNameOfSymptom(String nameOfSymptom) {
		this.nameOfSymptom = nameOfSymptom;
	}

	public int getWeightOfSymptom() {
		return weightOfSymptom;
	}

	public void setWeightOfSymptom(int weightOfSymptom) {
		this.weightOfSymptom = weightOfSymptom;
	}

	public boolean isCatched() {
		return isCatched;
	}

	public void setCatched(boolean isCatched) {
		this.isCatched = isCatched;
	}

	// This is abstract method for get String of symptom explanation.
	public abstract String getExplainationOfSymptom();
}
