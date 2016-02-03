package player;

public class Player {

	private String playerName;
	private String playerToAttack;
	private String attackMechanism;
	private String defenseMechanism;
	private int    attackSpeed;
	private int    defenseSpeed;
	private int    numberOfWoundsSustained;
	
	public Player(String name) {
		this.playerName = name;
	}
	
	public Player(String playerName, String playerToAttack, String attackMechanism, int attackSpeed, String defenseMechanism, int defenseSpeed) {
		this.playerName       = playerName;
		this.attackMechanism  = attackMechanism;
		this.attackSpeed      = attackSpeed;
		this.defenseMechanism = defenseMechanism;
		this.defenseSpeed     = defenseSpeed;
		this.playerToAttack   = playerToAttack;
		
		numberOfWoundsSustained = 0;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getAttackMechanism() {
		return attackMechanism;
	}
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public String getDefenseMechanism() {
		return defenseMechanism;
	}
	
	public int getDefenseSpeed() {
		return defenseSpeed;
	}
	
	public int getNumberOfWoundsSustained() {
		return numberOfWoundsSustained;
	}
	
	public void incrementNumberOfWoundsSustained() {
		numberOfWoundsSustained++;
	}
	
	public int setNumberOfWoundsToZeroWhenNoHit() {
		numberOfWoundsSustained = 0;
		
		return numberOfWoundsSustained;
	}
	
	public String getPlayerToAttack() {
		return playerToAttack;
	}
}
