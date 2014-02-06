package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagStringTest {

	private static net.minecraft.server.v1_6_R3.NBTTagString nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagString("", "bla bla bla");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagString.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagString.class);
	}

	@Test
	public void testNBTTagString() {
		final NBTTagString nbt = new NBTTagString(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagStringException() {
		new NBTTagString("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagString nbt = new NBTTagString(nmsTag);
		assertTrue(nbt.getData().equals(nmsTag.data));
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagString nbt = new NBTTagString(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagString nbt = new NBTTagString(nmsTag), equal = new NBTTagString(nmsTag);
		final NBTTagString diff = new NBTTagString(new net.minecraft.server.v1_6_R3.NBTTagString("", "This is a String"));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagString nbt = new NBTTagString(nmsTag), clone = (NBTTagString) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagString nbt = new NBTTagString(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
