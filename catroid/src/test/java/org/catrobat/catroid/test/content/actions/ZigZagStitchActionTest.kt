/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2023 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.test.content.actions

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.catrobat.catroid.content.Sprite
import org.catrobat.catroid.embroidery.ZigZagRunningStitch
import org.catrobat.catroid.formulaeditor.Formula
import org.catrobat.catroid.test.StaticSingletonInitializer.Companion.initializeStaticSingletonMethods
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ZigZagStitchActionTest {
    private var sprite: Sprite? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        initializeStaticSingletonMethods()
        sprite = Sprite("testSprite")
    }

    @Test
    fun testNormalBehavior() {
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        assertTrue(sprite?.runningStitch?.isRunning == true)
    }

    @Test
    fun testNormalBehaviorLengthSet() {
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(TEST_LENGTH, zzRunningStitch.length)
    }

    @Test
    fun testNormalBehaviourWidthSet() {
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(TEST_LENGTH, zzRunningStitch.width)
    }

    @Test
    fun testStartCoordinatesX() {
        val cordX = sprite?.look?.xInUserInterfaceDimensionUnit
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(cordX, zzRunningStitch.startXCoordinate)
    }

    @Test
    fun testStartCoordinatesY() {
        val cordY = sprite?.look?.yInUserInterfaceDimensionUnit
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(cordY, zzRunningStitch.startYCoordinate)
    }

    @Test
    fun testNullFormulaLength() {
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            null,
            Formula(TEST_WIDTH)
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(TEST_VALUE_INIT, zzRunningStitch.length)
    }

    @Test
    fun testNullFormulaWidth() {
        sprite?.actionFactory?.createZigZagStitchAction(
            sprite,
            SequenceAction(),
            Formula(TEST_LENGTH),
            null
        )?.act(1.0f)
        val zzRunningStitch = sprite?.runningStitch?.type as ZigZagRunningStitch
        assertEquals(TEST_VALUE_INIT, zzRunningStitch.width)
    }

    companion object {
        private const val TEST_LENGTH = 10f
        private const val TEST_WIDTH = 10f
        private const val TEST_VALUE_INIT = 0f
    }
}