# Userific — static site

Minimal, Apple‑style homepage that auto‑loads tools from `tools.json`. No ads on the homepage. Poppins font. Light by default with dark mode toggle.

## Structure
- `index.html` — homepage
- `style.css` — global styles
- `script.js` — theme + loader
- `tools.json` — list of tools (name, description, url)
- `privacy.html`, `terms.html`, `about.html`, `contact.html`
- `favicon.svg`, `robots.txt`, `sitemap.xml`

## Add a tool
1. Upload your tool HTML to `/tools/your-tool.html` in the repo.
2. Add an entry to `tools.json`:
```json
{ "name": "My Tool", "description": "What it does", "url": "tools/my-tool.html" }
```
3. Commit. The homepage will show it automatically.

## SEO
- Keep titles & descriptions on tool pages.
- Update `sitemap.xml` with new tool URLs (or automate in CI).
- Register domain in Google Search Console and submit the sitemap.

## Ads
- No ads on `index.html`. Place ad slots inside tool pages only.
