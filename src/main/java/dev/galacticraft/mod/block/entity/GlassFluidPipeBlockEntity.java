/*
 * Copyright (c) 2019-2021 Team Galacticraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.mod.block.entity;

import dev.galacticraft.mod.accessor.WorldRendererAccessor;
import dev.galacticraft.mod.api.block.entity.Colored;
import dev.galacticraft.mod.api.block.entity.Connected;
import dev.galacticraft.mod.api.block.entity.Pullable;
import dev.galacticraft.mod.block.special.fluidpipe.PipeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class GlassFluidPipeBlockEntity extends PipeBlockEntity implements Colored, Connected, Pullable {
    private boolean pull = false;

    public GlassFluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(GalacticraftBlockEntityType.GLASS_FLUID_PIPE, pos, state, 81000 / 50); //0.4B/s
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.readPullNbt(nbt);

        if (world.isClient) {
            ((WorldRendererAccessor) MinecraftClient.getInstance().worldRenderer).addChunkToRebuild(this.pos);
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.writePullNbt(nbt);
    }

    @Override
    public boolean isPull() {
        return this.pull;
    }

    @Override
    public void setPull(boolean pull) {
        this.pull = pull;
    }
}
