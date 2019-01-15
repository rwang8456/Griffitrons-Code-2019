package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


// @authors: Spencer Collins, Dakota Goldberg, and Leonard Kakinuma



public class ControlScheme2 extends TimedRobot {
   
    WPI_TalonSRX fLeft = new WPI_TalonSRX(0);
	WPI_TalonSRX bLeft = new WPI_TalonSRX(1);
	WPI_TalonSRX fRight = new WPI_TalonSRX(2);
	WPI_TalonSRX bRight = new WPI_TalonSRX(3);
    MecanumDrive phil = new MecanumDrive(fLeft, bLeft, fRight, bRight);
    Joystick GamerStick = new Joystick(0);
    
    @Override
    public void teleopInit() {
        fRight.setInverted(true);
        bRight.setInverted(true);
        


        
    }
    
    @Override
    public void teleopPeriodic() {
        /**Math to obtain the angle from the joystick location
         *  Then we grab the hypoteneuse for the magnitude.
         */
        double x = GamerStick.getX();
        double y = GamerStick.getY();
        double angle = Math.toDegrees(Math.atan(y/x));
        double magnitude = Math.sqrt((y*y)+(x*x))/(4*Math.sqrt(2));
        //creates a threshhold so it doesn't move unintentionally
        if(GamerStick.getTrigger()){
            //RAMMING SPEED!!
            phil.drivePolar(0.75, 90, 1);
           //phil.driveCartesian(0.5,0,0);
        }else{
        if(magnitude > 0.1){
            //checks  if its forward or backwards.
            if(y <= 0){
                angle += 90;
                phil.drivePolar(magnitude, angle, 0.75);
              // phil.driveCartesian(y, x, 0);
            } else{

               angle -= 90;
               phil.drivePolar(magnitude, angle, 0.75);
                //phil.driveCartesian(-y, x, 0);
            }
        }
    }
        

    }















    public void testPeriodic(){
        System.out.println("X: " + GamerStick.getX());
        System.out.println("Y: " + GamerStick.getY());
    }








}