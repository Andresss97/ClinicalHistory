
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />

	<xsl:template match="/">
		<html>
			<title>BABYLON STUDIO</title>

			<body>
				<table border="1">
					<th>ID</th>
					<th>NAME</th>
					<th>SURNAME</th>
					<th>NIF</th>
					<th>EMAIL</th>
					<th>MOBILEPHONE</th>
					<th>HOMEPHONE</th>
					<th>DOB</th>
					<th>GENDER</th>
					<th>USERNAME</th>
					<th>PASSWORD</th>
					<th>WEIGHT</th>
					<th>HEIGHT</th>

					<xsl:for-each select="patients">
						<tr>
							<td><i><xsl:value-of select="id"/></i></td>
							<td><i><xsl:value-of select="name" /></i></td>
							<td><i><xsl:value-of select="surname" /></i></td>
							<td><i><xsl:value-of select="nif" /></i></td>
							<td><i><xsl:value-of select="email" /></i></td>
							<td><i><xsl:value-of select="mobilephone" /></i></td>
							<td><i><xsl:value-of select="homephone" /></i></td>
							<td><i><xsl:value-of select="dob" /></i></td>
							<td><i><xsl:value-of select="gender" /></i></td>
							<td><i><xsl:value-of select="username" /></i></td>
							<td><i><xsl:value-of select="password" /></i></td>
							<td><i><xsl:value-of select="weight" /></i></td>
							<td><i><xsl:value-of select="height" /></i></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>

		</html>
	</xsl:template>
</xsl:stylesheet>