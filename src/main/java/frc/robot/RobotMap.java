package frc.robot;

public class RobotMap {
    //Ports On Robot
    //What Type Of Port
    static final public int DIO1 = 1;
    static final public int PWM1 = 1;
    static final public int CANBUS1 = 1;
    static final public int CANBUS2 = 2;
    static final public int CANBUS3 = 3;
	static final public int CANBUS4 = 4;
	static final public int CANBUS5 = 5;
	static final public int CANBUS6 = 6;
	static final public int CANBUS7 = 7;
	static final public int CANBUS8 = 8;
    
	//Motors
	static final public int DT_RIGHT_BACK = CANBUS1;
	static final public int DT_RIGHT_FORWARD = CANBUS2;
    static final public int DT_LEFT_BACK = CANBUS3;
	static final public int UPPER_SHOOTER = CANBUS4;
	static final public int LOWER_SHOOTER = CANBUS5;
	static final public int DT_LEFT_FORWARD = CANBUS6;
	static final public int INTAKE = PWM1;
	static final public int TURRET_SPINNER = CANBUS7;

    //Ports Off Robot
	//Joysticks
	public static final int JOYSTICK_DRIVE_LEFT = 0;
	public static final int JOYSTICK_DRIVE_RIGHT = 1;
	public static final int JOYSTICK_MANIUPLATOR = 2;
	public static final int GAMEPAD_MANIPULATOR = 2;
	public static final int GAMEPAD_ENDGAME = 3;

	//Joystick Buttons
	public static final int DRIVER_TRIGGER = 1;
	
	//Gamepad Buttons
	public static final int MANIPULATOR_LEFT_BUMPER = 5;
	public static final int MANIPULATOR_RIGHT_BUMPER = 6;
	public static final int MANIPULATOR_A_BUTTON = 1;
	public static final int MANIPULATOR_B_BUTTON = 2;
	public static final int MANIPULATOR_X_BUTTON = 3;
	public static final int MANIPULATOR_Y_BUTTON = 4;
	public static final int MANIPULATOR_START_BUTTON = 8;


	//Gamepad Axes
	public static final int MANIPULATOR_RIGHT_TRIGGER = 3;
	public static final int MANIPULATOR_LEFT_TRIGGER = 2;
	public static final int MANIPULATOR_LEFT_JOYSTICK_Y = 1;
	public static final int MANIPULATOR_RIGHT_JOYSTICK_Y = 5;
}
