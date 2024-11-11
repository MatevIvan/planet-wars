import shared.Fleet;
import shared.Planet;
import shared.PlanetWars;

public class DB9 {
	public static int numTurns = 0;
	public static int MaxShips = 0;
	public static int[] PID = new int[100];

	private static void Attack(PlanetWars pw, Planet source, int numShips) {

		Planet dest = null;
		int ignored = 0;
		if (numShips == 0)
			numShips = source.numShips() / 3;

		int dist;
		int BestDist = 99999;
		boolean Search = true;
		boolean attack = true;

		if (source.numShips() > 5 * source.growthRate()) {
			numShips = source.growthRate();
			for (Planet PID : pw.myPlanets()) {
				if (PID.getDanger() && !PID.equals(source)) {
					dist = pw.distance(source, PID);
					if (dist < 15) {
						attack = false;
						dest = PID;
						if (!source.getDanger()){
							numShips = source.numShips();
						}
					}
				}
			}
			if (attack) {
				for (Planet P : pw.notMyPlanets()) {
					if (Search) {
						if (P.owner() == 2
								|| (P.owner() == 0 && P.numShips() < 50)
								|| ignored > 5) {
							dist = pw.distance(source, P);
							if (Search) {
								if (dist < BestDist) {
									BestDist = dist;
									dest = P;

									if (source.numShips() > P.numShips()
											+ P.growthRate() * dist + 4
											* source.growthRate()
											&& P.owner() == 2) {
										dest = P;
										System.err.println("Strike!");
										numShips = P.numShips()
												+ P.growthRate() * dist + 1;
										Search = false;
									}
								}
							}
						}
					}
				}
			}
			if (dest != null) {
				pw.issueOrder(source, dest, numShips);
			}
		}
	}

	public static void doTurn(PlanetWars pw) {
		if (numTurns == 0) {
			numTurns = 1;
			int distance = pw.distance(pw.myPlanets().get(0), pw.enemyPlanets()
					.get(0));
			boolean go = true;

			if (distance > 8) {
				for (Planet P : pw.neutralPlanets()) {
					if (go) {
						int dist = pw.distance(pw.myPlanets().get(0), P);
						if (dist < 10) {
							if (P.numShips() < 40) {
								if (pw.myPlanets().get(0).numShips() > P
										.numShips() + 1) {
									if (MaxShips + P.numShips() < 100) {
										pw.issueOrder(pw.myPlanets().get(0), P,
												P.numShips() + 1);
										MaxShips += P.numShips() + 1;
									} else {
										return;
									}
								}
							}
						}
					}
				}
			}
		} else {
			int dist;
			int Fleets = 0;
			int[] DeltaD = new int[100];
			int[] EnemyFleets = new int[100];

			int DD;
			for (Planet source : pw.myPlanets()) {
				int numShips = 0;
				boolean Attack = true;
				int numFleet = 0;
				for (Fleet F : pw.enemyFleets()) {
					if (F.destinationPlanet() == source.planetID()) {

						dist = F.turnsRemaining();
						Fleets++;
						if (Fleets < 50) {
							DeltaD[Fleets] = dist;
							EnemyFleets[Fleets] = F.numShips();

							numFleet += F.numShips();
							DD = DeltaD[Fleets] - DeltaD[Fleets - 1];

							if (source.numShips() + source.growthRate() * DD > numFleet
									+ source.growthRate()
									&& source.numShips() + source.growthRate()
											* DeltaD[1] > EnemyFleets[1]
											+ source.growthRate()) {
								if (source.numShips() + source.growthRate()
										* DD == numFleet + source.growthRate()) {
									numShips = source.growthRate();
									source.setNotInDanger();
								} else {
									if (source.numShips() + source.growthRate()
											* DD > numFleet
											+ source.growthRate()) {
										numShips = source.growthRate() + 1;
										source.setNotInDanger();
									}
								}
							} else {
								Attack = false;
								source.setInDanger();
							}
						} else {
							Attack = false;
							source.setInDanger();
						}
					}
				}
				if (Attack) {
					Attack(pw, source, numShips);
				}
			}
		}
	}

	public static void main(String[] args) {
		String line = "";
		String message = "";
		int c;
		try {
			while ((c = System.in.read()) >= 0) {
				switch (c) {
				case '\n':
					if (line.equals("go")) {
						PlanetWars pw = new PlanetWars(message);
						doTurn(pw);
						pw.finishTurn();
						message = "";
					} else {
						message += line + "\n";
					}
					line = "";
					break;
				default:
					line += (char) c;
					break;
				}
			}
		} catch (Exception e) {
			// Owned.
		}
	}
}