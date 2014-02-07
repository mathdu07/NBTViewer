package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagEndTest {

	private static net.minecraft.server.v1_6_R3.NBTTagEnd nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagEnd();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagEnd.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagEnd.class);
	}
	
	@Test
	public void testCreateTag() {
		assertEquals(new NBTTagEnd(new net.minecraft.server.v1_6_R3.NBTTagEnd()), NBTTagEnd.createTag());
	}

	@Test
	public void testNBTTagEnd() {
		final NBTTagEnd nbt = new NBTTagEnd(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagEndException() {
		new NBTTagEnd("a string");
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagEnd nbt = new NBTTagEnd(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testClone() {
		final NBTTagEnd nbt = new NBTTagEnd(nmsTag), clone = (NBTTagEnd) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	

}
