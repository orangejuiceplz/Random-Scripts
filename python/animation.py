from manim import *


class LHopitalDerivation(Scene):
    def construct(self):
        # Title
        title = Text("L'Hôpital's Rule - Derivation", font_size=48)
        self.play(Write(title))
        self.wait(2)
        self.play(FadeOut(title))
        
        # Problem setup
        problem_text = Text("Finding the limit of indeterminate forms", font_size=36)
        self.play(Write(problem_text))
        self.wait(2)
        self.play(problem_text.animate.to_edge(UP))
        
        # Show the limit we want to evaluate
        limit_expr = MathTex(
            r"\lim_{x \to a} \frac{f(x)}{g(x)} = \frac{0}{0} \text{ or } \frac{\infty}{\infty}"
        )
        self.play(Write(limit_expr))
        self.wait(2)
        self.play(limit_expr.animate.scale(0.8).next_to(problem_text, DOWN))
        
        # Setup - Assumptions
        assumption_title = Text("Assumptions:", font_size=32, color=YELLOW)
        assumption_title.next_to(limit_expr, DOWN, buff=0.5).to_edge(LEFT)
        self.play(Write(assumption_title))
        
        assumptions = VGroup(
            MathTex(r"1. \, f(a) = g(a) = 0", font_size=32),
            MathTex(r"2. \, f'(a) \text{ and } g'(a) \text{ exist}", font_size=32),
            MathTex(r"3. \, g'(a) \neq 0", font_size=32)
        ).arrange(DOWN, aligned_edge=LEFT, buff=0.3)
        assumptions.next_to(assumption_title, DOWN, buff=0.3).to_edge(LEFT)
        
        self.play(Write(assumptions))
        self.wait(3)
        
        # Clear for derivation
        self.play(
            FadeOut(problem_text),
            FadeOut(limit_expr),
            FadeOut(assumption_title),
            FadeOut(assumptions)
        )
        
        # Start derivation
        deriv_title = Text("Derivation using Cauchy's Mean Value Theorem", font_size=36)
        self.play(Write(deriv_title))
        self.wait(2)
        self.play(deriv_title.animate.scale(0.7).to_edge(UP))
        
        # Step 1: Write the limit we want to find
        step1 = MathTex(
            r"\lim_{x \to a} \frac{f(x)}{g(x)}"
        )
        self.play(Write(step1))
        self.wait(2)
        
        # Step 2: Rewrite using f(a) = g(a) = 0
        step2 = MathTex(
            r"\lim_{x \to a} \frac{f(x) - f(a)}{g(x) - g(a)}"
        )
        self.play(Transform(step1, step2))
        self.wait(2)
        
        # Step 3: Apply Cauchy's Mean Value Theorem
        cauchy_text = Text("By Cauchy's Mean Value Theorem:", font_size=28, color=BLUE)
        cauchy_text.next_to(step1, DOWN, buff=0.8)
        self.play(Write(cauchy_text))
        
        step3 = MathTex(
            r"\frac{f(x) - f(a)}{g(x) - g(a)} = \frac{f'(c)}{g'(c)}"
        )
        step3.next_to(cauchy_text, DOWN, buff=0.4)
        self.play(Write(step3))
        
        # Note about c
        note = MathTex(
            r"\text{where } c \in (a, x) \text{ or } (x, a)",
            font_size=28,
            color=GRAY
        )
        note.next_to(step3, DOWN, buff=0.3)
        self.play(Write(note))
        self.wait(3)
        
        # Step 4: Take the limit
        limit_text = Text("Taking the limit as x → a:", font_size=28, color=GREEN)
        limit_text.next_to(note, DOWN, buff=0.6)
        self.play(Write(limit_text))
        
        step4 = MathTex(
            r"\lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(c)}{g'(c)}"
        )
        step4.next_to(limit_text, DOWN, buff=0.4)
        self.play(Write(step4))
        self.wait(2)
        
        # Step 5: Since c is between a and x, as x → a, we have c → a
        squeeze_text = Text("As x → a, we have c → a (squeeze theorem)", font_size=26, color=ORANGE)
        squeeze_text.next_to(step4, DOWN, buff=0.5)
        self.play(Write(squeeze_text))
        self.wait(2)
        
        # Clear for final result
        self.play(
            FadeOut(step1),
            FadeOut(cauchy_text),
            FadeOut(step3),
            FadeOut(note),
            FadeOut(limit_text),
            FadeOut(step4),
            FadeOut(squeeze_text),
            FadeOut(deriv_title)
        )
        
        # Final theorem
        final_title = Text("L'Hôpital's Rule", font_size=48, color=YELLOW)
        self.play(Write(final_title))
        self.wait(1)
        self.play(final_title.animate.to_edge(UP))
        
        # The theorem in a box
        theorem = MathTex(
            r"\lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)}",
            font_size=44
        )
        box = SurroundingRectangle(theorem, color=YELLOW, buff=0.3)
        theorem_group = VGroup(theorem, box)
        
        self.play(Write(theorem))
        self.play(Create(box))
        self.wait(2)
        
        # Conditions
        conditions_title = Text("Provided that:", font_size=32, color=BLUE)
        conditions_title.next_to(theorem_group, DOWN, buff=0.8)
        self.play(Write(conditions_title))
        
        conditions = VGroup(
            MathTex(r"\bullet \, \lim_{x \to a} \frac{f(x)}{g(x)} \text{ gives } \frac{0}{0} \text{ or } \frac{\infty}{\infty}", font_size=30),
            MathTex(r"\bullet \, f'(x) \text{ and } g'(x) \text{ exist near } a", font_size=30),
            MathTex(r"\bullet \, g'(x) \neq 0 \text{ near } a", font_size=30),
            MathTex(r"\bullet \, \lim_{x \to a} \frac{f'(x)}{g'(x)} \text{ exists or is } \pm\infty", font_size=30)
        ).arrange(DOWN, aligned_edge=LEFT, buff=0.3)
        conditions.next_to(conditions_title, DOWN, buff=0.4)
        
        self.play(Write(conditions), run_time=4)
        self.wait(3)
        
        # Example
        self.play(
            FadeOut(theorem_group),
            FadeOut(conditions_title),
            FadeOut(conditions)
        )
        
        example_title = Text("Example Application", font_size=40, color=GREEN)
        example_title.next_to(final_title, DOWN, buff=0.5)
        self.play(Write(example_title))
        
        example = MathTex(
            r"\lim_{x \to 0} \frac{\sin(x)}{x}"
        )
        example.next_to(example_title, DOWN, buff=0.5)
        self.play(Write(example))
        self.wait(1)
        
        # Check form
        form_check = MathTex(
            r"= \frac{\sin(0)}{0} = \frac{0}{0}",
            color=ORANGE
        )
        form_check.next_to(example, RIGHT, buff=0.3)
        self.play(Write(form_check))
        self.wait(2)
        
        # Apply L'Hôpital
        apply_text = Text("Apply L'Hôpital's Rule:", font_size=28)
        apply_text.next_to(example, DOWN, buff=0.6)
        self.play(Write(apply_text))
        
        solution = MathTex(
            r"\lim_{x \to 0} \frac{\sin(x)}{x} = \lim_{x \to 0} \frac{\cos(x)}{1} = \frac{\cos(0)}{1} = 1"
        )
        solution.next_to(apply_text, DOWN, buff=0.4)
        self.play(Write(solution))
        self.wait(3)
        
        # Final highlight
        result_box = SurroundingRectangle(solution, color=GREEN, buff=0.2)
        self.play(Create(result_box))
        self.wait(2)
        
        self.play(
            *[FadeOut(mob) for mob in self.mobjects]
        )
        
        # Closing
        closing = Text("Q.E.D.", font_size=60, color=YELLOW)
        self.play(Write(closing))
        self.wait(2)

