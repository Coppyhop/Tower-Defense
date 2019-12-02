#version 150 core

in vec3 position;
in vec2 textureCoords;

out vec2 passTexture;

void main(void) {
	gl_Position = vec4(position,1.0);
	passTexture = textureCoords;
}
