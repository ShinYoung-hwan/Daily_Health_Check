// This class for store user data.
public class Person {
	// to increase readability, the enum is used.
	public enum symptomType {
		Fever, Loss_Of_Appetite, Fatigue, Loss_Of_Smell, Shortness_Of_breath, Cough, Coughing_Up_sputum,
		Muscle_Aches_and_Pain
	}

	private String userName;
	private int userAge;
	private int numOfSymptoms;
	// Use array to store symptoms.
	private Symptoms[] symptoms = new Symptoms[8];

	public Person(String userName, int userAge) {
		this.userName = userName;
		this.userAge = userAge;
		this.numOfSymptoms = 0;

		this.symptoms[symptomType.Fever.ordinal()] = new Fever();
		this.symptoms[symptomType.Loss_Of_Appetite.ordinal()] = new LossOfAppetite();
		this.symptoms[symptomType.Fatigue.ordinal()] = new Fatigue();
		this.symptoms[symptomType.Loss_Of_Smell.ordinal()] = new LossOfSmell();
		this.symptoms[symptomType.Shortness_Of_breath.ordinal()] = new ShortnessOfBreath();
		this.symptoms[symptomType.Cough.ordinal()] = new Cough();
		this.symptoms[symptomType.Coughing_Up_sputum.ordinal()] = new CoughingUpSputum();
		this.symptoms[symptomType.Muscle_Aches_and_Pain.ordinal()] = new MuscleAchesAndPain();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public int getNumOfSymptoms() {
		return numOfSymptoms;
	}

	public void setNumOfSymptoms(int numOfSymptoms) {
		this.numOfSymptoms = numOfSymptoms;
	}

	public Symptoms[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Symptoms[] symptoms) {
		this.symptoms = symptoms;
	}

	public void plusNumOfSymptoms() {
		this.numOfSymptoms++;
	}

	// This method is for get total weight of symptoms.
	public int getTotalWeight() {
		int weightSum = 0;

		for (Symptoms symptom : this.symptoms) {
			weightSum += symptom.getWeightOfSymptom();
		}

		return weightSum;
	}
}
