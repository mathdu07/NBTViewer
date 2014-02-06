package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagIntTest {

	private static net.minecraft.server.v1_6_R3.NBTTagInt nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagInt("", 125);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagInt.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagInt.class);
	}

	@Test
	public void testNBTTagInt() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagIntException() {
		new NBTTagInt("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag), equal = new NBTTagInt(nmsTag);
		final NBTTagInt diff = new NBTTagInt(new net.minecraft.server.v1_6_R3.NBTTagInt("", 47));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag), clone = (NBTTagInt) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagInt nbt = new NBTTagInt(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}
