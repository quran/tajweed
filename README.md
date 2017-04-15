## Tajweed Highlighting Experiments

بسم الله الرحمن الرحيم
*In the name of Allah, Most Gracious, Most Merciful*

The purpose of this project is to experiment with "tajweed syntax highlighting," or writing algorithms to color code the various tajweed rules as is found in the tajweed mus7af. There is currently a mode for the standard madani tajweed mus7af and another for the naskh tajweed mus7af (which highlight tajweed slightly differently).

If Allah makes it easy and things go well, the idea is that the output from this project can be used by many projects.

**Note:** this project is still a work in progress. It is by no means complete, nor does it perfectly match the tajweed mus7af yet. It is being open sourced in case people in the community would like to help accelerate its development.

### Exporters

Tajweed contains a set of exporters to be able to export the tajweed data that it collects. Currently, there are 3 of these:

- TextExporter (just outputs in plain text, useful for debugging)
- ImageExporter (outputs an image)
- HtmlExporter (outputs html - currently only works on Firefox, see #15).

### Examples

Here is an example using the image exporter:

![Sura Fil, ayah 4](example.png)

### Useful Links

- [Tanzil Fonts](http://tanzil.net/docs/quranic_fonts)
- [Tajweed Mushaf](http://www.islamicbulletin.org/services/details.aspx?id=260)
- [Naskh Tajweed Mushaf](http://www.zikr.co.uk/content/view/112/180/)
