package Floor.FType.FShaders;

import Floor.FType.FRender.FireBallRenderer;
import arc.Core;
import arc.graphics.gl.Shader;

import static arc.Core.files;
import static mindustry.Vars.tree;

public class FireBallShader extends Shader {
    public float[] fires;
    public float[] color;
    public float[] rotations;

    public FireBallShader() {
        super(files.internal("shaders/screenspace.vert"),
                tree.get("shaders/fireball.frag"));
    }

    @Override
    public void apply(){
        setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
        setUniformf("u_resolution", Core.camera.width, Core.camera.height);

        setUniformi("num", FireBallRenderer.places.size);
        setUniform4fv("fires", fires, 0, fires.length);
        setUniform4fv("colors", color, 0, color.length);
        setUniform4fv("rotations", rotations, 0, rotations.length);
    }
}
