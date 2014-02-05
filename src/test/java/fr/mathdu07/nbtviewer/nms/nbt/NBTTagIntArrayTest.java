package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagIntArrayTest {

	private static net.minecraft.server.v1_6_R3.NBTTagIntArray nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init("1.6.4-R2.0");
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagIntArray("", new int[] {0, 4, -7, 6});
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagIntArray.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagIntArray.class);
	}

	@Test
	public void testNBTTagIntArray() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagIntArrayException() {
		new NBTTagIntArray("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag);
		assertTrue(Arrays.equals(nbt.getData(), nmsTag.data));
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag), equal = new NBTTagIntArray(nmsTag);
		final NBTTagIntArray diff = new NBTTagIntArray(new net.minecraft.server.v1_6_R3.NBTTagIntArray("", new int[] {4, 8, 25}));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag), clone = (NBTTagIntArray) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagIntArray nbt = new NBTTagIntArray(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
