package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagByteArrayTest {

	private static net.minecraft.server.v1_6_R3.NBTTagByteArray nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init("1.6.4-R2.0");
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagByteArray("", new byte[] {-2, 4, 3, -15});
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagByteArray.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagByteArray.class);
	}

	@Test
	public void testNBTTagByteArray() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagByteArrayException() {
		new NBTTagByteArray("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag);
		assertTrue(Arrays.equals(nbt.getData(), nmsTag.data));
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag), equal = new NBTTagByteArray(nmsTag);
		final NBTTagByteArray diff = new NBTTagByteArray(new net.minecraft.server.v1_6_R3.NBTTagByteArray("", new byte[] {1, 2, 3}));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag), clone = (NBTTagByteArray) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagByteArray nbt = new NBTTagByteArray(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
