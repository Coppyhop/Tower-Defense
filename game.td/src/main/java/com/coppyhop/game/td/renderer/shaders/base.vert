#version 130

in vec3 position;
in vec2 textureCoords;

uniform vec3 entityXYZ;
uniform mat4 entityPos;
uniform vec3 coloration;

out vec3 color;
out vec2 passTexture;

void main(void) {
	vec4 entity = vec4(position,1.0)*entityPos;
	gl_Position = vec4(entity.x-1 + entityXYZ.x, entity.y+1-entityXYZ.y, entity.z, 1.0);
	passTexture = textureCoords;
	color = coloration;
}
