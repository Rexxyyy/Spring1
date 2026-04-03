# Payment Plan Performance Case Study

## Objective
Develop an initial hypothesis for why payment plans fail or do not complete on time, and propose product next steps that improve on-time completion while minimizing downside risk.

## Framing and assumptions
Given the available context, I would define **"plan success"** as: a payment plan that reaches expected payoff (or active, on-schedule status) without cancellation, chronic skips, or prolonged delinquency.

I am making these assumptions:
1. Plan-level outcomes can be inferred from `paymentplans`, `transactions`, and `actions`.
2. Failure modes include (a) payment failures that never recover, (b) repeated rescheduling/skips, and (c) borrower cancellation.
3. Monetary units require careful normalization: in January, most monetary fields are in cents; debt values in `debts` are explicitly an exception.

## Initial hypothesis
Payment plan underperformance is likely driven by a **payment-method + schedule mismatch** that creates avoidable cash-flow friction, amplified by unrestricted plan edits.

In plain terms:
- Some borrowers choose due dates or cadences that do not align with their paycheck timing.
- Debit cards likely have higher failure sensitivity (insufficient funds, card lifecycle issues) than ACH for recurring obligations.
- When early installments fail, borrowers use skips and date pushes as coping behaviors; repeated friction then cascades into cancellation.

This produces a common path: **first failed charge -> reschedule/skip loop -> cancellation or long delay**.

## How I would validate the hypothesis quickly
I would run a staged funnel and driver analysis:

### 1) Build a plan outcome funnel
For each plan cohort (by plan start month):
- Activated plans
- Plans with first installment successful
- Plans with >=1 failed transaction
- Plans with >=1 skip / date change action
- Cancelled plans
- Completed on time

Cut this by:
- cadence (`weekly`, `biweekly`, `monthly`, `1st+15th`)
- payment method (`ACH`, `debit`)
- debt-size buckets
- client (from `debts`)

### 2) Time-to-event and early-warning analysis
- Compare success probability when the first installment succeeds vs fails.
- Measure how many days from first failure to cancellation.
- Quantify whether >N modifications in first 30 days predicts late/non-completion.

### 3) Unit economics and risk checks
- Convert all amounts to dollars with explicit table-level exceptions.
- Track expected collections lift and downside risk per intervention.
- Monitor guardrails: cancellation rate, borrower complaint rate, and recovered-after-failure rate.

## Recommendations to product (prioritized)

### 1) Add “payday-aligned scheduling” defaults at plan creation
**What to change**
- During plan setup, suggest dates based on observed borrower behavior (or explicit payday input).
- Encourage cadences tied to paycheck rhythm (biweekly or 1st/15th for semi-monthly earners).

**Why**
Misaligned due dates are a plausible root cause of NSF-like failures and frequent rescheduling.

**Experiment**
A/B test current scheduling UX vs payday-guided defaults.

**Primary success metric**
+ On-time completion rate at 60/90 days.

### 2) Introduce smart payment-method routing and backup collection
**What to change**
- Nudge eligible borrowers toward ACH for recurring plans.
- Add backup method capture (where compliant) and pre-charge reminders.
- Retry logic based on failure reason and timing (e.g., next payday proximity).

**Why**
Method reliability differences can materially impact recurring success.

**Experiment**
Randomize ACH-first recommendation + backup method prompt.

**Guardrails**
No increase in cancellations, disputes, or borrower support burden.

### 3) Limit harmful plan volatility while preserving flexibility
**What to change**
- Keep skip/cadence-edit features, but add “friction with support”: a borrower can still modify, but after repeated edits receives a guided flow (e.g., right-size installment or temporary hardship plan) instead of unconstrained postponement.
- Trigger proactive outreach after repeated failures/skips.

**Why**
Frequent edits are often a symptom of affordability mismatch; guided restructuring should outperform repeated delay.

**Experiment**
Policy test: unlimited edits (control) vs guided restructure after threshold.

### 4) Build an early-risk score for intervention targeting
**What to change**
- Score plans in first 14 days using signals: first payment status, method type, number of edits/skips, installment-to-balance ratio.
- Route high-risk plans to reminders, agent outreach, or auto-restructure options.

**Why**
Most deterioration likely happens early; targeted intervention is higher ROI than blanket messaging.

## Measurement plan
For each experiment, pre-register:
- **Primary metric:** % plans completed on time.
- **Secondary metrics:** total dollars collected, plan survival at 30/60/90 days, recovery after first failure.
- **Guardrails:** cancellation rate, complaint/contact rate, and delinquency aging.

Use staged rollout by client segment to reduce revenue risk. Stop-loss rules should auto-pause any treatment with statistically significant guardrail harm.

## Key risks and mitigations
- **Data quality / unit mismatch:** enforce one normalization layer and reconciliation checks by table.
- **Selection bias:** use randomized tests, not only observational comparisons.
- **Over-constraining borrowers:** preserve opt-out paths and transparent communication.

## 30-day execution plan
1. Instrument and publish baseline funnel + segment cuts.
2. Ship payday-aligned scheduling test.
3. Launch ACH-first + backup method prompt test.
4. Stand up early-risk score v1 and targeted intervention playbook.

---

### AI usage disclosure
I used AI as a writing and structuring assistant to: (a) organize hypotheses, (b) sharpen experiment design, and (c) improve concision and executive readability. I verified that recommendations remain grounded in the provided business context and explicitly stated assumptions where direct data evidence was unavailable.
