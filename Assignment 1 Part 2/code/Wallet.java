/**
 * Represents a digital wallet with balance, minimum balance, and auto-pay status.
 */
public class Wallet {
    private float balance;          // Current balance.
    private float minBalance;       // Minimum required balance.
    private boolean autoPayEnabled; // Auto-pay status.

    /** Retrieves the current wallet balance. */
    public float checkBalance() { return this.balance; }

    /** Adds the specified amount to the wallet balance. */
    public boolean addMoney(float amount) { this.balance += amount; return true; }

    /** Deducts the specified amount from the wallet balance. */
    public boolean deductMoney(float amount) { this.balance -= amount; return true; }

    /** Checks if the wallet has the minimum required balance. */
    public boolean hasMinimumBalance() { return this.balance >= this.minBalance; }

    /** Enables auto-pay if not already enabled. */
    public boolean enableAutoPay() { if (!this.autoPayEnabled) this.autoPayEnabled = true; return true; }

    /** Disables auto-pay if currently enabled. */
    public boolean disableAutoPay() { if (this.autoPayEnabled) this.autoPayEnabled = false; return true; }

    /** Checks if auto-pay is enabled. */
    public boolean isAutoPayEnabled() { return this.autoPayEnabled; }
}
