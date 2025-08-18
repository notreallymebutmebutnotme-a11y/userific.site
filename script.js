document.addEventListener("DOMContentLoaded", () => {
  const toolsContainer = document.getElementById("tools-container");

  fetch("tools.json")
    .then(response => response.json())
    .then(tools => {
      // Detect if we are on GitHub Pages
      let baseUrl = window.location.origin;
      let pathName = window.location.pathname;

      // If hosted under a repo (like /userific.site/), keep that part
      let repoName = "";
      if (pathName.includes("/userific.site")) {
        repoName = "/userific.site";
      }

      tools.forEach(tool => {
        const toolCard = document.createElement("div");
        toolCard.className = "tool-card";
        toolCard.innerHTML = `
          <h2>${tool.name}</h2>
          <p>${tool.description}</p>
          <a href="${baseUrl}${repoName}/tools/${tool.slug}/" class="btn">Open Tool</a>
        `;
        toolsContainer.appendChild(toolCard);
      });
    })
    .catch(error => console.error("Error loading tools:", error));
});
