package org.usfirst.frc.team3663.robot;

import java.util.Optional;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Wraps risky hardware access with Optional<>.
 */
public class HardwareUtil {
	private HardwareUtil() {
	}

	public static <T> Optional<T> getHardware(Supplier<? extends T> supplier) {
		try {
			return Optional.ofNullable(supplier.get());
		} catch (final Exception e) {
			System.err.println(
					"WARNING: Hardware not detected. Using n default instead");
			e.printStackTrace();

			return Optional.empty();
		}
	}

	public static Optional<DoubleSolenoid> getDoubleSolenoid(int forwardChannel,
			int reverseChannel) {
		return getHardware(
				() -> new DoubleSolenoid(forwardChannel, reverseChannel));
	}

	public static Optional<DigitalInput> getDigitalInput(int id) {
		return getHardware(() -> new DigitalInput(id));
	}
}
