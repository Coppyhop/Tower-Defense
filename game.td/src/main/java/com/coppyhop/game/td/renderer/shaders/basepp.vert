#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec2 passTexture;
out float passTime;

uniform float time;

void main(void) {
	gl_Position = vec4(position.x*2-1,position.y*2+1,0,1.0);
	passTexture = textureCoords;
	passTime = time;
}
