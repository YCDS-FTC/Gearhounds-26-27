package org.firstinspires.ftc.teamcode.Colemansfile;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Motor Test", group = "Linear OpMode")
public class MotorTest extends OpMode {

    public Servo servo;

    public DcMotorEx m1;
    public DcMotorEx m2;
    selected motor = selected.both;
    page menu = page.run;
    settings setting = settings.direction;
    boolean m1frwd = true, m2frwd = true;
    boolean m1brake = true, m2brake = true;
    double m1speed = 0.5, m2speed = 0.5;
    running power = running.power;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        m1 = hardwareMap.get(DcMotorEx.class, "motor1");
        m2 = hardwareMap.get(DcMotorEx.class, "motor2");
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m1.setDirection(DcMotor.Direction.FORWARD);
        m2.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addLine("MOTOR TESTING 101\nmotor config names: 'motor1' & 'motor2'");
        telemetry.update();
    }
//0.73 0.58
    @Override
    public void loop() {
        servo.setPosition(servo.getPosition() + gamepad1.right_stick_y * 0.001);

        if (menu == page.run) {
            //change speed
            if (gamepad1.dpad_right) {
                if (motor == selected.one) {
                    m1speed += 0.002;
                } else if (motor == selected.two) {
                    m2speed += 0.002;
                } else {
                    m1speed += 0.002;
                    m2speed += 0.002;
                }
            } else if (gamepad1.dpad_left) {
                if (motor == selected.one) {
                    m1speed -= 0.002;
                } else if (motor == selected.two) {
                    m2speed -= 0.002;
                } else {
                    m1speed -= 0.002;
                    m2speed -= 0.002;
                }
            }
            //power clamp
            if (power == running.power) {
                m1speed = clamp(m1speed, -1, 1);
                m2speed = clamp(m2speed, -1, 1);
            }
            //motor selection
            if (gamepad1.dpadDownWasPressed()) {
                if (motor == selected.both) {
                    motor = selected.one;
                } else if (motor == selected.one) {
                    motor = selected.two;
                }
            } else if (gamepad1.dpadUpWasPressed()) {
                if (motor == selected.one) {
                    motor = selected.both;
                } else if (motor == selected.two) {
                    motor = selected.one;
                }
            }
            //to settings
            else if (gamepad1.xWasPressed()) {
                setting = settings.direction;
                menu = page.settings;
            }
            //run motor
            else if (gamepad1.a) {
                if (power == running.velocity) {
                    if (motor == selected.one) {
                        m1.setVelocity(m1speed * 2500);
                    } else if (motor == selected.two) {
                        m2.setVelocity(m2speed * 2500);
                    } else {
                        m1.setVelocity(m1speed * 2500);
                        m2.setVelocity(m2speed * 2500);
                    }
                } else {
                    if (motor == selected.one) {
                        m1.setPower(m1speed);
                    } else if (motor == selected.two) {
                        m2.setPower(m2speed);
                    } else {
                        m1.setPower(m1speed);
                        m2.setPower(m2speed);
                    }
                }
            } else {
                m1.setPower(0);
                m2.setPower(0);
            }

            telemetry.addLine("=== Motor Testing ===");
            if (motor == selected.one) {
                telemetry.addLine("both motors");
                if (power == running.velocity) {
                    telemetry.addData("> motor1 set :", m1speed * 2500);
                    telemetry.addData("motor2 set :", m2speed * 2500);
                } else {
                    telemetry.addData("> motor1 set :", m1speed);
                    telemetry.addData("motor2 set :", m2speed);
                }
            } else if (motor == selected.two) {
                telemetry.addLine("both motors");
                if (power == running.velocity) {
                    telemetry.addData("motor1 set :", m1speed * 2500);
                    telemetry.addData("> motor2 set :", m2speed * 2500);
                } else {
                    telemetry.addData("motor1 set :", m1speed);
                    telemetry.addData("> motor2 set :", m2speed);
                }
            } else {
                telemetry.addLine("> both motors");
                if (power == running.velocity) {
                    telemetry.addData("motor1 set :", m1speed * 2500);
                    telemetry.addData("motor2 set :", m2speed * 2500);
                } else {
                    telemetry.addData("motor1 set :", m1speed);
                    telemetry.addData("motor2 set :", m2speed);
                }
            }
            telemetry.addLine("\n---MOTOR 1---");
            telemetry.addData("Encoder", m1.getCurrentPosition());
            telemetry.addData("Velocity", "%.1f", m1.getVelocity());
            telemetry.addData("Rotate Forward ", m1frwd);
            telemetry.addData("Zero Brake ", m1brake);
            telemetry.addLine("\n---MOTOR 2---");
            telemetry.addData("Encoder", m2.getCurrentPosition());
            telemetry.addData("Velocity", "%.1f", m2.getVelocity());
            telemetry.addData("Rotate Forward ", m2frwd);
            telemetry.addData("Zero Brake ", m2brake);
            telemetry.addLine("\n---Instructions---");
            telemetry.addLine("↑ or ↓ to select\n<- or -> to Change Power\nX for Settings\nA to Turn On");
        }
        if (menu == page.settings) {
            //Move Through Settings
            if (gamepad1.dpadDownWasPressed()) {
                if (setting == settings.direction) {
                    setting = settings.zero;
                } else if (setting == settings.zero) {
                    setting = settings.reset;
                } else if (setting == settings.reset) {
                    setting = settings.swap;
                }
            } else if (gamepad1.dpadUpWasPressed()) {
                if (setting == settings.zero) {
                    setting = settings.direction;
                } else if (setting == settings.reset) {
                    setting = settings.zero;
                } else if (setting == settings.swap) {
                    setting = settings.reset;
                }
            } else if (gamepad1.dpadRightWasPressed()) {
                if (setting == settings.direction) {
                    if (motor == selected.one) {
                        if (m1frwd) {
                            m1frwd = false;
                            m1.setDirection(DcMotor.Direction.REVERSE);
                        } else {
                            m1frwd = true;
                            m1.setDirection(DcMotor.Direction.FORWARD);
                        }
                    } else if (motor == selected.two) {
                        if (m2frwd) {
                            m2frwd = false;
                            m2.setDirection(DcMotor.Direction.REVERSE);
                        } else {
                            m2frwd = true;
                            m2.setDirection(DcMotor.Direction.FORWARD);
                        }
                    } else {
                        if (m1frwd) {
                            m1frwd = false;
                            m1.setDirection(DcMotor.Direction.REVERSE);
                        } else {
                            m1frwd = true;
                            m1.setDirection(DcMotor.Direction.FORWARD);
                        }
                        if (m2frwd) {
                            m2frwd = false;
                            m2.setDirection(DcMotor.Direction.REVERSE);
                        } else {
                            m2frwd = true;
                            m2.setDirection(DcMotor.Direction.FORWARD);
                        }
                    }
                } else if (setting == settings.zero) {
                    if (motor == selected.one) {
                        if (m1brake) {
                            m1brake = false;
                            m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                        } else {
                            m1brake = true;
                            m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        }
                    } else if (motor == selected.two) {
                        if (m2brake) {
                            m2brake = false;
                            m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                        } else {
                            m2brake = true;
                            m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        }
                    } else {
                        if (m1brake) {
                            m1brake = false;
                            m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                        } else {
                            m1brake = true;
                            m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        }
                        if (m2brake) {
                            m2brake = false;
                            m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                        } else {
                            m2brake = true;
                            m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        }
                    }
                } else if (setting == settings.reset) {
                    if (motor == selected.one) {
                        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        m1.setDirection(DcMotor.Direction.FORWARD);
                        m1brake = true;
                        m1frwd = true;
                    } else if (motor == selected.two) {
                        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        m2.setDirection(DcMotor.Direction.FORWARD);
                        m2brake = true;
                        m2frwd = true;
                    } else {
                        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                        m1.setDirection(DcMotor.Direction.FORWARD);
                        m1brake = true;
                        m1frwd = true;
                        m2.setDirection(DcMotor.Direction.FORWARD);
                        m2brake = true;
                        m2frwd = true;
                    }
                } else if (setting == settings.swap) {
                    if (power == running.velocity) {
                        power = running.power;
                    } else {
                        power = running.velocity;
                    }
                }
            }
            //To run
            else if (gamepad1.xWasPressed()) {
                menu = page.run;
            }
            m1.setPower(0);
            m2.setPower(0);
            telemetry.addLine("=== Settings ===");
            telemetry.addData("motor num :", motor);
            if (motor == selected.one) {
                telemetry.addLine(sel(settings.direction) + "Direction : " + (m1frwd ? "Forward" : "Reverse"));
                telemetry.addLine(sel(settings.zero) + "Zero Mode : " + (m1brake ? "Brake" : "Float"));
            } else if (motor == selected.two) {
                telemetry.addLine(sel(settings.direction) + "Direction : " + (m2frwd ? "Forward" : "Reverse"));
                telemetry.addLine(sel(settings.zero) + "Zero Mode : " + (m2brake ? "Brake" : "Float"));
            } else {
                telemetry.addLine(sel(settings.direction) + "Directions : " + (m1frwd != m2frwd ? "Mixed" : (m1frwd ? "Forward" : "Reverse")));
                telemetry.addLine(sel(settings.zero) + "Zero Mode : " + (m1brake != m2brake ? "Mixed" : (m1brake ? "Brake" : "Float")));
            }
            telemetry.addLine(sel(settings.reset) + "Reset Motors");
            telemetry.addLine(sel(settings.swap) + "Change (Velocity/Power)");

            telemetry.addLine("\n---Instructions---");
            telemetry.addLine("↑ or ↓ to select\n-> to Change Setting\nX for Motor Running");

        }
        telemetry.update();
    }

    @Override
    public void stop() {
        m1.setPower(0);
        m2.setPower(0);
    }

    public double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(max, x));
    }

    String sel(settings s) {
        return (setting == s) ? "> " : "  ";
    }


    enum selected {both, one, two}

    enum page {run, settings}

    enum settings {direction, zero, reset, swap}

    enum running {velocity, power}
}
