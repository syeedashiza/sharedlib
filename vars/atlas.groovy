import hudson.plugins.cobertura.targets.CoverageResult;
import hudson.plugins.cobertura.targets.CobetureCoveragePaser
import hudson.plugins.cobertura.targets.CobetureBuildAction

def call(report){
public Boolean reportCodeCoverage(MavenBuild build) throws IOException {
    try {
      CoberturaBuildAction cba = build.getAction(CoberturaBuildAction.class);
      if (cba == null) {
        File cvgxml = new File(build.getRootDir(), "coverage.xml");
        CoverageResult result = CoberturaCoverageParser.parse(cvgxml, null, new HashSet<String>());
        result.setOwner(build);
        CoberturaBuildAction o = CoberturaBuildAction.load(result, null, null, false, false, false, false, false, false, 0);
        build.addAction(o);
      } else {
        return false;
      }
    } catch (NullPointerException e) {
      return false;
    }
    return true;
 }
reportCodeCoverage("coverage.xml")
}
