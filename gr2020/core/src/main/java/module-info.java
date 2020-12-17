module moodtracker.core {
  requires transitive com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.datatype.jsr310;
  opens moodtracker.core to com.fasterxml.jackson.databind, com.fasterxml.jackson.datatype;

  exports moodtracker.core;
  exports moodtracker.json;
}
