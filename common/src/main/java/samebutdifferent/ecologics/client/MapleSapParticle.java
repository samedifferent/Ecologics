package samebutdifferent.ecologics.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.FluidState;
import samebutdifferent.ecologics.registry.ModParticleTypes;

public class MapleSapParticle extends SingleQuadParticle 
{
    private SimpleParticleType type;

    protected MapleSapParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.xd *= 0.3F;
        this.yd = this.random.nextFloat() * -0.2F;
        this.zd *= 0.3F;
        this.type = type;
        this.gravity = 0.1F;
        this.setColor(1F, 0.8F, 0.6F);
        this.setAlpha(0.85F);
    }
    
    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }
    
    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            if (this.type == ModParticleTypes.FALLING_MAPLE_SAP) {
                this.yd = this.yd - this.gravity;
                this.move(this.xd, this.yd, this.zd);
                this.postMoveUpdate();
                if (!this.removed) {
                    this.xd *= 0.98F;
                    this.yd *= 0.98F;
                    this.zd *= 0.98F;
                    BlockPos pos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidState = this.level.getFluidState(pos);
                    if (this.y < pos.getY() + fluidState.getHeight(this.level, pos)) {
                        this.remove();
                    }
                }
            }
            else {
                if (!this.removed) {
                    this.xd *= 0.98F;
                    this.yd *= 0.98F;
                    this.zd *= 0.98F;
                    BlockPos pos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidState = this.level.getFluidState(pos);
                    if (this.y < pos.getY() + fluidState.getHeight(this.level, pos)) {
                        this.remove();
                    }
                }
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
            if (this.type == ModParticleTypes.DRIPPING_MAPLE_SAP) {
                this.level.addParticle(ModParticleTypes.FALLING_MAPLE_SAP, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }
    }
    
    protected void postMoveUpdate() {
        if (this.type == ModParticleTypes.DRIPPING_MAPLE_SAP) {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }
    }

    // A standard Provider factory to be registered on the client side
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, final RandomSource random) {
            return new MapleSapParticle(type, level, x, y, z, this.sprite.get(random));
        }
    }
}
