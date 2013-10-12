
package realmrelay.crypto;

public class RC4 {

	private byte[] a;
	private int b;
	private int c;

	/**
	 * Creates new RC4 cipher object with string key
	 * @param key
	 */
	public RC4(String key) {
		this(hexStringToBytes(key));
	}

	/**
	 * Creates new RC4 cipher object with byte array key
	 * @param bytes
	 */
	public RC4(byte[] bytes) {
		this.a = new byte[256];
		for (int i = 0; i < this.a.length; i++) {
			this.a[i] = (byte) i;
		}
		this.b = 0;
		this.c = 0;
		int i = 0;
		int j = 0;
		if (bytes.length == 0) {
			throw new IllegalArgumentException("invalid rc4 key");
		}
		for (int k = 0; k < this.a.length; k++) {
			j = (bytes[i] & 0xFF) + (this.a[k] & 0xFF) + j & 0xFF;
			int l = this.a[k];
			this.a[k] = this.a[j];
			this.a[j] = (byte) l;
			i = (i + 1) % bytes.length;
		}
	}

	/**
	 * Cipher bytes and update cipher
	 * @param bytes
	 */
	public void cipher(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			this.b = this.b + 1 & 0xFF;
			this.c = (this.a[this.b] & 0xFF) + this.c & 0xFF;
			int j = this.a[this.b];
			this.a[this.b] = this.a[this.c];
			this.a[this.c] = (byte) j;
			j = (this.a[this.b] & 0xFF) + (this.a[this.c] & 0xFF) & 0xFF;
			bytes[i] = (byte) (bytes[i] ^ this.a[j]);
		}
	}

	private static byte[] hexStringToBytes(String key) {
		if (key.length() % 2 != 0) {
			throw new IllegalArgumentException("invalid hex string");
		}
		byte[] arrayOfByte = new byte[key.length() / 2];
		char[] c = key.toCharArray();
		for (int i = 0; i < c.length; i += 2) {
			StringBuilder localStringBuilder = new StringBuilder(2).append(c[i]).append(c[(i + 1)]);
			int j = Integer.parseInt(localStringBuilder.toString(), 16);
			arrayOfByte[(i / 2)] = (byte) j;
		}
		return arrayOfByte;
	}

}
